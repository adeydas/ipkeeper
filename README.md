IP Keeper
========

Search for EC2 instance details based on the "Name" tag (full and partial matches).


How to use it
=============

Run the program as 

java -jar ipkeeper.jar [tag] [credentials_file]

Compile as

mvn compile assembly:single


The credentials file should contain the access key and access secret in the following format:

[Access_Key]
[Access_Secret]


Support
=======
Feature requests/questions: http://abhis.ws:9090/browse/IP/


Thanks
======
To Sudhanshu Iyer for the idea (siyer.info)
