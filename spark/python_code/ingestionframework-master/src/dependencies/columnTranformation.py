# -*- coding: utf-8 -*-
"""
Created on 01/05/20 12:27 PM

@author : Nirav Langaliya
"""

# File Name : columnTranformation.py

# Enter feature description here

# Enter steps here

from functools import reduce

def snakeCaseHeaderFun (df):
    return reduce(
        lambda new_df, col_name: new_df.withColumnRenamed(col_name, to_snake_case(col_name)),
        df.columns,
        df
    )

def to_snake_case(s):
    return s.lower().replace(" ", "_")