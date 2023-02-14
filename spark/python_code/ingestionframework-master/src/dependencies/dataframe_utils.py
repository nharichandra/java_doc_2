# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""
from typing import List

from pyspark.sql import DataFrame
from pyspark.sql import functions as F
from pyspark.sql.types import StringType
from config.config_objects import ColumnNameTypeLength
from dependencies.schema_utilties import data_type_dict
from dependencies.datetime_utilities import *

def dataframe_add_columns(
        dataframe:DataFrame,
        addColumns: List[ColumnNameTypeLength]
):

    for new_column in addColumns:

        if new_column.columnValue:
            column_value = new_column.columnValue

        if new_column.columnName not in dataframe.columns:
            if new_column.columnValue == "current_date":
                column_value = get_current_date()
            if new_column.columnValue == "epoch":
                column_value = get_epoch()

            dataframe = dataframe.withColumn(
                new_column.columnName,
                F.lit(column_value).cast(
                    data_type_dict.get(new_column.columnType, StringType)
                )
            )
        else:
            dataframe = dataframe.withColumn(
                new_column.columnName,
                dataframe[new_column.columnName].cast(
                    data_type_dict.get(new_column.columnType, StringType)
                )
            )

    return dataframe
