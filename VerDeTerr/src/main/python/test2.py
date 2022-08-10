import sys
import pandas as pd

train = pd.read_csv('MBTI_train.csv', encoding='ISO 8859-1', header=None, names=['type', 'posts'])

train2 = pd.DataFrame()
for i, type in enumerate(train['type'].unique()):
    df = train.loc[train['type']==type]
    if(i%2==0):
        train2 = pd.concat([train2, df.head(20)])
    else:
        train2 = pd.concat([train2, df.tail(20)])

print("train2")
print(train2['type'].value_counts())
train2.to_csv("train2.csv", index=False, header=False)