# AES2016
Aes encryptor in libgdx
This is a simple open Source AES encryptor, 

With libgdx



### PLAY 


### FASTLY 


### SPECS
	* Cipher/Uncipher : If the Text is starting by IV, the Cipher button will uncipher it. Otherwise, it will cypher it. So I can only uncipher a text starting by the word IV.
	
		* Uncipher : 
			* 1/ Read IV:  		from "=" to [newline]. This should be base64 encrypted otherwise error.  
			* 2/ Base64 decode: the text between "-------" So I get a byte array (byte[])
			* 3/ AES uncipher:  Byte[] -> String I am printing in the textArea

		* Cipher : 
			* 1/ Calculate IV:	Randomly, It will be printed after IV1= and then print a newline, 
			* 2/ AES cipher:	The whole String in textArea
			* 3/ Base64 encode: The byte[] -> Base64 string printed between "----------"

### REFERENCES 
	* [Libgdx] (https://github.com/libgdx/libgdx)
		* [Gradle on commandLine] (https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline)
	* 

### License
 Open source : This code  is licensed under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0.html), meaning you
can use it free of charge, without strings attached in commercial and non-commercial projects.
END
