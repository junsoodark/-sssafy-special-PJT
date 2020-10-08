import json
import pandas as pd
import requests

def readJSON():
    api_key = ''
    file_path = "song4.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    df = df.iloc[0:1000]

    for i, row in df.iterrows():
        artist = row['artist']
        title = row['song_name']
        req = requests.get('https://orion.apiseeds.com/api/music/lyric/' + artist + '/' + title + '?apikey=' + api_key)
        if req.status_code == 200:
            json_data = req.json()
            lyric = json_data['result']['track']['text']
            df.loc[i, 'lyric'] = lyric
        else:
            df = df.drop(i)

    del df['index']

    print(df)
    #df.to_json('re-lyrics11.json', orient='table')


if __name__ == '__main__':
    readJSON()