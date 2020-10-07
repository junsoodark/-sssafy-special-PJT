# This is a sample Python script.

# Press ⌘R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

import json
import pandas as pd


def readJSON():
    file_path = "./song_meta.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')
    df.rename(columns={"song_gn_gnr_basket":"genre"}, inplace=True)

    del df['artist_id_basket']

    df2 = df.iloc[200000:250000]
    print(df2)

    for i, row in df2.iterrows():
        if len(row['genre']) != 0:
            if row['genre'][0] != 'GN0900':
                df2 = df2.drop(i)
    print(df2)

    df2.to_json('re.json', orient='table')


if __name__ == '__main__':
    readJSON()

