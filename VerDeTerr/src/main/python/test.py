# -*- coding: utf-8 -*-
import pymysql.cursors
import pandas as pd
import pickle
# from sklearn.model_selection import train_test_split
import os.path
import sys
import re
if sys.version_info[0] < 3: 
    from StringIO import StringIO
else:
    from io import StringIO

from datetime import date



print ("HELLO" + sys.argv[1])

def testprint(a):
    a = sys.argv[1]
    # print(a)

    return a

print(testprint(sys.argv[1]))

# Connect to the database
connection = pymysql.connect(host='localhost',
                            user='root',
                            password='0000',
                            db='board',
                            charset='utf8mb4',
                            cursorclass=pymysql.cursors.DictCursor)

def mysqlConnect(userid):
    try:
        
        cursor = connection.cursor()
        extract_query = "SELECT `answer1`,`answer2`,`answer3`,`answer4`,`answer5`,`answer6`,`answer7`,`answer8`,`answer9`,`answer10`,`answer11`,`answer12`,`answer13`,`answer14`,`answer15`,`answer16` FROM `mbti_ml` WHERE id = '"+ userid + "' order by idx desc limit 1"
        cursor.execute(extract_query)



        rows = cursor.fetchall()
        print(rows)

        for i in rows :
           
            value_list = list(i.values())
            
            value = " ".join(value_list)
            clean_value = re.sub('[-=+,#/\?:^.@*\"※~ㆍ!』‘|\(\)\[\]`\'…》\”\“\’·]', '',value)
            print(clean_value)

        today = date.today()
       
        insert_query="INSERT INTO `Mbti_ML_Output` VALUES (null,'" + userid + "','"+str(today)+"','"+clean_value+"','userType')"
        cursor.execute(insert_query)
        cursor.execute("commit")
        print('data import1')
        TESTDATA = StringIO(""" """ + value + """ """ )
        print('data import')
        data_dir = './MBTI_dataset/'
        
        train = pd.read_csv(os.path.join(data_dir, 'MBTI_train.csv'), encoding='ISO 8859-1', header=None, names=['type', 'posts'])
        test = pd.read_csv(TESTDATA, header=None, names=['posts'])
        print('data use')
        X = train['posts'] # features
        y = train['type']  # labels
        filename = 'mbti_svm_v10.sav'
        print(str(filename))
        # X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
        # text_clf = pickle.load(open(filename, 'rb'))
        # predictions = text_clf.predict(X_test)
        # predictions = text_clf.predict(test['posts'])



        # prediction = predictions[0]
        

        
        # update_query="UPDATE `MBTI_ML_OUTPUT` SET `UserType`= '" +prediction+ "' WHERE id = '"+userid + "' order by idx desc limit 1"
        # cursor.execute(update_query)
        # cursor.execute("commit")

        # return prediction

        

        

    finally:
        cursor.close()
        connection.close()


#db에다가 시작 시간을 집어 넣음
# mysqlConnect('dsfaasdf')
# mysqlConnect('qqqqq')
# mysqlConnect('asafasg')
# mysqlConnect('testid3')
# mysqlConnect('gggg')
# mysqlConnect('testid1111')
print(mysqlConnect(sys.argv[1]))

#db에다가 끝을 알림
print("프린트 테스트")