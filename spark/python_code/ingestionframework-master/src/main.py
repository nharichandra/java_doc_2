# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

"""
###################
# from ingestion framework dir
# spark-submit --jars ojdbc6.jar,databricks_delta.jar,spark-xml_2.11-0.5.0.jar src/main.py C:/tmp/pythonConfig/config_sample.json
# spark-submit --jars kafka.jar src/main.py C:/tmp/pythonConfig/config_kafka.json
# spark-submit --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.4.4 src/main.py C:/tmp/pythonConfig/config_kafka.json
# spark-submit --jars databricks_delta.jar src/main.py C:/tmp/pythonConfig/config_sample_job.json
# spark-submit --packages com.databricks:spark-xml_2.10:0.4.1 --jars ojdbc6.jar,databricks_delta.jar src/main.py C:/tmp/pythonConfig/config_sample.json
# spark-submit src/main.py C:/tmp/pythonConfig/config_sample_2.json

# # spark-submit --jars ojdbc6.jar,databricks_delta.jar src/main.py C:/tmp/pythonConfig/config_sample_db_inc.json
# # spark-submit --jars ojdbc6.jar,databricks_delta.jar src/main.py C:/tmp/pythonConfig/config_sample_db_no_inc.json

# spark-submit --py-files Ingestion_Framework-0.0.1-py3.7.egg src/main.py C:/tmp/pythonConfig/config_sample_2.json

### cloud
# spark-submit  /dbfs/FileStore/tables/IngestionFramework/src/main.py /dbfs/FileStore/tables/config/config_sample_file.json
###################
"""

"""
Change Log and Pending task:

Need to Add Logging funcationality
"""

#import logging
import sys
import json

#import pprint

def main():
    """
    Main program which initiates reading configuration json, for driving the program to
    "Batch" or "Stream" mode
    :return:
    """
    system_arguments = sys.argv[1:]
    #sys.path.append('/dbfs/FileStore/tables/IngestionFramework/src/')
    # print(system_arguments)

    # fileReader()

    config_file = ",".join([str(arg) for arg in system_arguments if ".json" in str(arg).lower()])
    property_file = ",".join([str(arg) for arg in system_arguments if ".prop" in str(arg).lower()])

    if not property_file:
        print("No Property File Used..")

    if not config_file:
        raise Exception( " Error: No Config File Found! ")
    else:
        read_config(config_file)


def read_config(config_file):
    with open(config_file) as json_file:
        config_json = json.load(json_file)

    from config.config_objects import Jobs
    job_all = Jobs.from_json(json.dumps(config_json))

    from dependencies.pretty_print import pretty_print

    pretty_print(' Mode of Execution: ' + job_all.mode )
    for job in job_all.jobs:
        pretty_print(' Jobs identified: ' + str(job) + "\n")
    print("\n\n" + "*" * 100)

    if job_all.mode.lower() == "batch":
        from job.batch import batch_job
        batch_job(job_all)

    elif job_all.mode.lower() == "stream":
        from job.direct_stream import direct_stream_job
        direct_stream_job(job_all)

    pretty_print(' All Jobs processed sucessfully ')


if __name__ == '__main__':
    main()