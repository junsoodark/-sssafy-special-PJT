import requests
import json
import pandas as pd
import math

def calc():
    file_path = "emtion.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    df = df.head(5)

    happy = 0.999927
    sad = 0.00001
    angry = 0.000002
    fear = 0.000006
    excited = 0.00005
    indifferent = 0.000005
    a = [0.999927, 0.00001, 0.000002, 0.000006, 0.00005, 0.000005]

    for i, row in df.iterrows():
        b = list()
        b.append(row['happy'])
        b.append(row['sad'])
        b.append(row['angry'])
        b.append(row['fear'])
        b.append(row['excited'])
        b.append(row['indifferent'])
        sim = similarity(a, b)
        print(sim)

    print(df)


def similarity(a, b):
    top = a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + a[3]*b[3] + a[4]*b[4] + a[5]*b[5]
    bottom = math.sqrt(a[0]*a[0]+a[1]*a[1]+a[2]*a[2]+a[3]*a[3]+a[4]*a[4]+a[5]*a[5]) * math.sqrt(b[0]*b[0]+b[1]*b[1]+b[2]*b[2]+b[3]*b[3]+b[4]*b[4]+b[5]*b[5])
    sim = top / bottom

    return sim


if __name__ == '__main__':
    calc()