import json
import pandas as pd

def readJSON():
    file_path = "eeee2.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    for i, row in df.iterrows():
        df.loc[i, 'issue_date'] = row['issue_date'][0:6]

    print(df)
    del df['level_0']

    df.to_json('eeee2.json', orient='table')


if __name__ == '__main__':
    readJSON()