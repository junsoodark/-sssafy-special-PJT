import requests
import json
import pandas as pd

def analysis():
    api_key = "xh4Su0sghFDW8wlgyle0RwBDVKHcU83Y7zTdN8HOp9M"

    file_path = "songswithlyrics.json"
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')

    df = df.iloc[1800:2000]

    for i, row in df.iterrows():
        lyric = row['lyric']
        response = requests.post("https://apis.paralleldots.com/v5/emotion", data={"api_key": api_key, "text": lyric})
        dictionary = response.json()
        if 'emotion' in dictionary.keys():
            df.loc[i, 'happy'] = dictionary['emotion']['happy']
            df.loc[i, 'sad'] = dictionary['emotion']['sad']
            df.loc[i, 'angry'] = dictionary['emotion']['angry']
            df.loc[i, 'fear'] = dictionary['emotion']['fear']
            df.loc[i, 'excited'] = dictionary['emotion']['excited']
            df.loc[i, 'indifferent'] = dictionary['emotion']['indifferent']

    del df['_id']

    df.to_json('eeee2.json', orient='table')


if __name__ == '__main__':
    analysis()