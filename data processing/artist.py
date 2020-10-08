import json
import pandas as pd

def readJSON():
    file_path = "song5.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    for i, row in df.iterrows():
        if len(row['artist_name_basket']) != 0:
            df.loc[i, 'artist'] = row['artist_name_basket'][0]

    del df['index']
    del df['genre']
    del df['id']
    del df['artist_name_basket']
    print(df.columns)
    print(df)

    # df.to_json('song5.json', orient='table')


if __name__ == '__main__':
    readJSON()

