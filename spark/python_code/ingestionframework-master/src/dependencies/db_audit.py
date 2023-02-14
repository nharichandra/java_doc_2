# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

# Create audit table - one-time
# import org.apache.spark.sql.types._
# import org.apache.spark.sql.Row
# val audit_path = "C:/tmp/samplePythonOutput/audit"
# val schema = StructType( Seq( StructField("INGESTION_ID", StringType, false),  StructField("INGESTION_DESC", StringType, true), StructField("INGESTION_TIMESTAMP", TimestampType, false), StructField("INC_COL", StringType, false),	  StructField("AUDIT_ID", StringType, false) 	  ))
# val df = spark.createDataFrame(sc.emptyRDD[Row], schema)
# df.write.format("delta").save(audit_path)

# val df2 = spark.read.format("delta").load("C:/tmp/samplePythonOutput/audit/")
# df2.registerTempTable("audit_table")
# spark.sql("insert into audit_table values('item_master','sample desc',CAST(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(), 'yyyy-MM-dd HH:mm:ss.000') AS TIMESTAMP),'last_update_datetime','18-APR-03')")


from pyspark.sql import functions as F, DataFrame
from dependencies.datetime_utilities import get_timestamp
from dependencies.pretty_print import pretty_print
from dependencies.schema_utilties import get_jdbc_options, get_jdbc_reader


def db_audit_query(
        url,
        user,
        password,
        driver,
        dbtable,
        dbIncremental
):
    from dependencies.spark import spark_session
    pretty_print(" Initiating Audit Read ")

    audit_table_df = spark_session.read.format("delta").load(dbIncremental.auditTablePath).filter(
        F.col("INGESTION_ID") == dbIncremental.ingestionID
    )

    min_value = audit_table_df.orderBy(F.col("INGESTION_TIMESTAMP"), ascending=False).select(
        "AUDIT_ID"
    ).first()[0]

    pretty_print("Min Value : " + min_value)
    max_query = dbIncremental.maxQuery.replace("{minValue}", min_value)
    pretty_print("Max Query : " + max_query)

    options_incremental = get_jdbc_options(
        url,
        user,
        password,
        driver,
        max_query,
        num_partitions=0,
        partition_column=None,
        lower_bound=None,
        upper_bound=None,
        query_timeout=0,
        fetch_size=0,
        predicate_pushdown=None
    )

    max_value = get_jdbc_reader(options_incremental).load().first()[0]

    pretty_print("Max value : " + str(max_value))

    query = dbtable.replace(
        "{minValue}", min_value
    ).replace(
        "{maxValue}", str(max_value)
    )

    pretty_print("Final query : " + query)

    return query, max_value


def db_audit_write(
        audit_table_path,
        ingestion_id,
        max_value,
        dataframe: DataFrame
):
    from dependencies.spark import spark_session

    audit_table_df = spark_session.read.format("delta").load(audit_table_path).filter(
        F.col("INGESTION_ID") == ingestion_id
    ).orderBy(F.col("INGESTION_TIMESTAMP"), ascending=False).limit(1)

    audit_table_df = audit_table_df.withColumn("INGESTION_TIMESTAMP", F.lit(get_timestamp()))

    if bool(dataframe.head(1)):
        audit_table_df = audit_table_df.withColumn("AUDIT_ID", F.lit(str(max_value)))

    audit_table_df.write.format("delta").mode("Append").save(audit_table_path)

    pretty_print("Audit write successful ")
    pass
