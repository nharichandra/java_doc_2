# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

from pyspark.streaming import StreamingContext

from config.config_objects import KafkaInput, Jobs
from dependencies.kafka_utilities import kafka_utils
from dependencies.schema_utilties import get_schema


def direct_stream_job(jobs_all):

    from dependencies.spark import spark_context
    batch_duration = jobs_all.streamParam.batchDuration
    streaming_context = StreamingContext(spark_context, batch_duration)

    for job in jobs_all.jobs:
        print("\n\n " + "* " * 30 + " Initiating Stream Job -- " + str(job.name) + " " + "* " * 30)

        # schema_dict = {}
        # for schema_file in job.schemaParams.schemaFiles:
        #     schema_dict[schema_file] = get_schema(job.schemaParams.schemaFilePath + '/' + schema_file)

        if job.kafkaInputs:
            for kafka_input in job.kafkaInputs:
                #kafka_input = KafkaInput(kafka_input)
                kafka_utils(streaming_context,kafka_input)
                streaming_context.start()
                streaming_context.awaitTermination(jobs_all.streamParam.batchDuration)
                streaming_context.stop(False, True)




                
















    pass