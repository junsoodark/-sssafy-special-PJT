# This is a sample Python script.

# Press ⌘R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

import json
import pandas as pd

def readJSON():
    file_path = "./song_meta_short.json"
    exceptGenre = ['GN0700', 'GN1500', 'GN1600', 'GN1700', 'GN1800', 'GN1900', 'GN2000', 'GN2100', 'GN2200', 'GN2300',
                   'GN2400', 'GN2800', 'GN2900', 'GN3000']
    with open(file_path, encoding='utf-8', mode='r') as json_file:
        json_data = json.load(json_file)

    data = json_data
    df = pd.DataFrame.from_dict(data, orient='columns')
    df.rename(columns={"song_gn_gnr_basket":"genre"}, inplace=True)
    for i, row in df.iterrows():
        for gen in exceptGenre:
            if gen in row['genre']:
                df = df.drop(i)
                break;

    print(len(df))

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    readJSON()

