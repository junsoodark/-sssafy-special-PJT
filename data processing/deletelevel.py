import json
import pandas as pd

def readJSON():
    file_path = "eeee1.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    del df['level_0']

    df.to_json('eeee1.json', orient='table')


if __name__ == '__main__':
    readJSON()