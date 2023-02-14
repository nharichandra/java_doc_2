# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""
from typing import List

from pyspark.sql import DataFrame

# from src.config.config_objects import ColumnNameType
# from src.dependencies.dataframe_utils import dataframe_add_columns
# from src.dependencies.schema_utilties import get_options

from config.config_objects import ColumnNameTypeLength
from dependencies.dataframe_utils import dataframe_add_columns
from dependencies.schema_utilties import get_options
from dependencies.columnTranformation import snakeCaseHeaderFun

def file_writer(
        dataframe:DataFrame,
        format,
        path,
        mode,
        header,
        snakeCaseHeader,
        delimiter,
        num_partitions,
        partition_columns,
        addColumns: List[ColumnNameTypeLength],
        selectCols
):

    if selectCols:
        print(selectCols)
        dataframe = dataframe.select(selectCols)

    # Add columns
    if addColumns:
        dataframe = dataframe_add_columns(dataframe,addColumns)

    if snakeCaseHeader:
        dataframe = snakeCaseHeaderFun(dataframe)

    # Repartition dataframe
    if num_partitions > 0:
        dataframe = dataframe.repartition(num_partitions) #.write.mode(mode).partitionBy(partition_columns)

    # Partition By column list
    if partition_columns:
        writer = dataframe.write.mode(mode).partitionBy(partition_columns)
    else:
        writer = dataframe.write.mode(mode)

    options = get_options(
        header = header, delimiter = delimiter
    )

    for option_name in options:
        writer = writer.option(option_name, options[option_name])

    if format.lower() == 'csv':
        writer.csv(path)
    elif format.lower() == "json":
        writer.json(path)
    elif format.lower() == "parquet":
        writer.parquet(path)
    elif format.lower() == "avro":
        writer.format("avro").save(path)
    elif format.lower() == "delta":
        writer.format("delta").save(path)
    elif format.lower() == "xml":
        writer.xml(path)
    elif format.lower() == "text":
        writer.text(path)
        pass
    else:
        raise Exception(" Unknown data write format! ")