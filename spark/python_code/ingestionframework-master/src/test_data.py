# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

from pyspark.sql import SparkSession

spark = SparkSession \
    .builder \
    .appName("Python Spark SQL basic example") \
    .config("spark.some.config.option", "some-value") \
    .getOrCreate()

parquetFile = spark.read.parquet("/Users/nlangaliya/Downloads/IngestionFramework/OutputData/parquet/part-00000-0b8180f7-3afe-4d6e-b330-ab64fd1abcaf-c000.snappy.parquet")

parquetFile.createOrReplaceTempView("parquetFile")

sampleDF = spark.sql("SELECT * FROM parquetFile")

sampleDF.show()

sampleDF.printSchema()