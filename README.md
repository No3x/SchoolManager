# Data
Executed Steps to fill some data

1. Start application and create a new schedule named `Test`
2. Fill some data with sql
The Database is located at `%APPDATA%/LuDev Studio/School Manager/save.db`

Create a new Subject
```
INSERT INTO "Subjects" ("ID", "Name", "Teacher", "Room", "Color") VALUES (0, 'First Sub', 'A', '12', '00ff00');
```
Reference the subject in the schedule
```
UPDATE "Schedule_Test" SET "1" = 0 WHERE "Lesson" = 0 AND "1" IS NULL AND "2" IS NULL AND "3" IS NULL AND "4" IS NULL AND "5" IS NULL AND "6" IS NULL AND "7" IS NULL
```

This fills the schedule like this:

| Montag 	| Dienstag       	| Mittwoch 	| Donnerstag 	| Freitag 	|
|--------	|----------------	|----------	|------------	|---------	|
| 1      	| First Sub A 12 	|          	|            	|         	|
| 2      	|                	|          	|            	|         	|
| .      	| .              	| .         | .          	| .        	|
| 8      	|                	|          	|            	|         	|

# Colors
The specified color 00ff00 (lime) is shown without issue:

![unbenannt](https://user-images.githubusercontent.com/2690708/44846036-80e9cd00-ac4f-11e8-8c6c-afeb4e9d3921.JPG)