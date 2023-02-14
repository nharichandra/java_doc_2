# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

from dependencies.db_audit import db_audit_write
from dependencies.pretty_print import pretty_print
from publish.write_file import file_writer


def batch_job(jobs_all):
    '''
    Batch job which consume File or Database input
    Data is loaded in DataFrame and Finally written as File Out
    :param jobs_all:
    :return:
    '''
    for job in jobs_all.jobs:
        pretty_print(" Initiating Batch Job -- " + str(job.name))

        dataframe_dict = {}
        dbms_audit_dict = {}

        # Identify type of input
        if job.fileInputs:
            for file_input in job.fileInputs:
                df = read_file(file_input)
                df.show(1, False)
                df.printSchema()
                dataframe_dict[file_input.dataFrameName] = df

        if job.dbmsInputs:
            for dbms_input in job.dbmsInputs:
                df, max_value = read_table(dbms_input)
                # df.show(1, False)
                if max_value is not None:
                    dbms_audit_dict[dbms_input.dataFrameName] = max_value
                dataframe_dict[dbms_input.dataFrameName] = df

        if job.fileOutputs:
            for file_output in job.fileOutputs:
                file_writer(
                    dataframe_dict[file_output.dataFrameName],
                    file_output.format,
                    file_output.fileOutPath,
                    file_output.mode,
                    file_output.header,
                    file_output.snakeCaseHeader,
                    file_output.delimiter,
                    file_output.numPartitions,
                    file_output.partitionBy,
                    file_output.addColumns,
                    file_output.selectCol
                )

        if job.dbmsInputs:
            for dbms_input in job.dbmsInputs:
                if dbms_input.dataFrameName in dbms_audit_dict:
                    db_audit_write(
                        dbms_input.dbIncremental.auditTablePath,
                        dbms_input.dbIncremental.ingestionID,
                        dbms_audit_dict[dbms_input.dataFrameName],
                        dataframe_dict[dbms_input.dataFrameName]
                    )

        pretty_print( " Job Completed  -- " + str(job.name) )


def read_file(file_input):
    from consume.read_file import file_reader
    return file_reader(
        file_input.fileInPath,
        file_input.dataFormat,
        file_input.snakeCaseHeader,
        file_input.enforceSchemaFile,
        file_input.numPartitions,
        file_input.selectCol
    )

def read_table(dbms_input):
    from consume.read_table import table_reader
    return table_reader(
        dbms_input.driver,
        dbms_input.url,
        dbms_input.user,
        dbms_input.password,
        dbms_input.dbtable,
        dbms_input.dbIncremental,
        dbms_input.numPartitions,
        dbms_input.partitionColumn,
        dbms_input.lowerBound,
        dbms_input.upperBound,
        dbms_input.queryTimeout,
        dbms_input.fetchsize,
        dbms_input.pushDownPredicate
    )