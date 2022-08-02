import sys
import csv

def main():
    print("hello world before")
    import pandas as pd
    # data_dir = './MBTI_dataset/'
    # train = pd.read_csv('./MBTI_dataset/MBTI_train.csv', encoding='ISO 8859-1', header=None, names=['type', 'posts'])
    with open('./MBTI_dataset/MBTI_train.csv', newline='', encoding='ISO 8859-1') as csvfile:
        train = csv.reader(csvfile, delimiter=' ', quotechar='|')
        for row in train[:10]:
            print(', '.join(row))
        # train = csv.reader('./MBTI_dataset/MBTI_train.csv', encoding='ISO 8859-1')
        # print(train)
    print("after the dataframe")

# print(sys.argv[1])
main()