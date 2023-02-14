***************************************************************************************************************************
**********************************************  Pyspark - Ingestion Framework *********************************************
***************************************************************************************************************************



PySpark based data ingestion framework which executes Batch and Stream Jobs, for consuming data from various types of 
sources.

Project 				: IngestionFramework
Source Root Directory 	: src


---------------------------------------------------------------------------------------------------------------------------
	  ----------------------------------------------  Pre-requisites ---------------------------------------------
---------------------------------------------------------------------------------------------------------------------------


			+------------------+---------+
			|Software/Language | Version |
			+------------------+---------+
			|Python	           | 3.7.5   |
			+------------------+---------+
			|Spark	           | 2.4.4   |
			+------------------+---------+
			|Scala	           | 2.11    |
			+------------------+---------+
			|    	           |         |
			+------------------+---------+


---------------------------------------------------------------------------------------------------------------------------
	  ----------------------------------------------  Execution  ---------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

Sample configuration file for File and Database ingestion is available in src/resources directory

To execute spark-submit, please run below command from IngestionFramework directory:


	spark-submit src/main.py src/resources/config_sample_2.json

In case of external Jar dependency, 
		e.g. jdbc Jar for connecting to Oracle, Jar for supporting Databricks delta format
Then, please execute spark-submit as follows:

	spark-submit --jars ojdbc6.jar,databricks_delta.jar,spark-xml_2.11-0.5.0.jar src/main.py C:/tmp/pythonConfig/config_sample.json


If external Jar files are configured in SPARK_HOME, then "--jars" parameter is not required
	
	spark-submit  src/main.py C:/tmp/pythonConfig/config_sample.json

#### PySpark

If you need to install or upgrade PySpark, run:



```python
pip install --upgrade pyspark
```

Run PySpark with the Delta Lake package:

```python
pyspark --packages io.delta:delta-core_2.11:0.5.0
```

Run PySpark with the avro package:

```python
pyspark --packages org.apache.spark:spark-avro_2.11:2.4.5
```

```
spark-submit --packages io.delta:delta-core_2.11:0.5.0 $CODE_PATH/main.py $CODE_PATH/resources/demo_config_csv2delta.json
```