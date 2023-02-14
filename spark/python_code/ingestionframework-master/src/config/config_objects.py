# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""
from dataclasses import dataclass, field
from dataclasses_json import dataclass_json
from typing import List

# @dataclass
# class QC:
#     recordCount: int
#     threshold: float

# @dataclass_json
@dataclass
class ColumnNameTypeLength:
    columnName: str
    columnType: str
    columnValue: str = ''
    columnLength: int = 1

@dataclass
class schemaParameter:
    schemaFilePath : str
    schemaFiles: List[str]
    schemaColumnName: str = None

@dataclass
class streamParameter:
    batchDuration: int = 2000
    stopStreamGracefully : bool = False
    timeOut: int = None

@dataclass
class sslParameter:
    truststoreLocation: str
    truststorePassword: str
    protocol: str = "ssl"
    keystoreLocation: str = None
    keystorePassword: str = None
    keyPassword: str = None
    endpoint: str = "https"

# @dataclass_json
@dataclass
class DataFormat:
    dataFormat: str
    header: bool = None
    delimiter: str = None
    inferSchema: bool = None
    multiLine: bool = None
    roottag: str = None
    rowtag: str = None
    colNameTypeLength: List[ColumnNameTypeLength] = field(default_factory=list)

# @dataclass_json
@dataclass
class FileInput:
    dataFrameName: str
    fileInPath: str
    dataFormat: DataFormat
    archivalPath: str = None
    snakeCaseHeader: bool = None
    selectCol: List[str] = field(default_factory=list)
    numPartitions: int = 0
    enforceSchemaFile: str = None

@dataclass
class DBMSIncremental:
    maxQuery: str
    auditTablePath: str
    ingestionID: str

@dataclass
class DBMSInput:
    dataFrameName: str
    url: str
    user: str
    password: str
    customSchema: str = None
    driver: str = None
    dbtable: str = None
    dbIncremental: DBMSIncremental = None
    numPartitions: int = 0
    partitionColumn: str = None
    lowerBound: str = None
    upperBound: str = None
    queryTimeout: int = 0
    fetchsize: int = 10
    #batchsize: int = 1000  -- for insert
    #truncate: boo; = False -- for truncate
    pushDownPredicate: bool = True

@dataclass
class KafkaInput:
    dataFrameName: str
    server: str
    topics: List[str]
    consumerGroup: str
    startingOffsets: str = "latest"
    endingOffsets : str = "latest"
    autoCommit: bool = False
    autoCommitInterval: int = 2000
    failOnDataLoss : bool = False
    ssl: sslParameter = None

# @dataclass_json
@dataclass
class FileOutput:
    dataFrameName: str
    format: str
    fileOutPath: str
    mode: str = 'Append'
    header: bool = None
    snakeCaseHeader: bool = None
    delimiter: str = None
    numPartitions: int = 0
    selectCol: List[str] = field(default_factory=list)
    partitionBy: List[str] = field(default_factory=list)
    addColumns: List[ColumnNameTypeLength] = field(default_factory=list)

# @dataclass_json
@dataclass
class Job:
    name: str
    description: str
    fileInputs: List[FileInput] = field(default_factory=list)
    fileOutputs: List[FileOutput] = field(default_factory=list)
    dbmsInputs: List[DBMSInput] = field(default_factory=list)
    kafkaInputs: List[KafkaInput] = field(default_factory=list)
    schemaParams: schemaParameter = None

@dataclass_json
@dataclass
class Jobs:
    mode: str = "batch"
    jobs: List[Job] = field(default_factory=list)
    streamParam: streamParameter = None