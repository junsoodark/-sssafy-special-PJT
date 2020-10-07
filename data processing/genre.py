import json
import pandas as pd

def readJSON():
    file_path = "eeee2.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    for i, row in df.iterrows():
        if len(row['song_gn_dtl_gnr_basket']) != 0:
            df.loc[i, 'genre'] = row['song_gn_dtl_gnr_basket'][0]

    del df['song_gn_dtl_gnr_basket']
    print(df.columns)
    print(df)

    df.to_json('eeee2.json', orient='table')


if __name__ == '__main__':
    readJSON()

