import json
import pandas as pd
import requests

def readJSON():
    api_key = ""
    file_path = "songs.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    for i, row in df.iterrows():
        artist = row['artist']
        song_name = row['song_name']
        q = song_name + " " + artist
        response = requests.get("https://www.googleapis.com/youtube/v3/search?key=" + api_key + "&part=id&q=" + q)
        result = response.json()
        if 'items' in result.keys():
            df.loc[i, 'youtubeId'] = result['items'][0]['id']['videoId']

    del df['_id']
    print(df)
    df.to_json('songswithlink.json', orient='table')


if __name__ == '__main__':
    readJSON()