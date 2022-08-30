# Copyright (c) 2022, Vicky Ye
# All rights reserved.

# This source code is licensed under the BSD-style license found in the
# LICENSE file in the root directory of this source tree. 

import plotly.graph_objects as go

songList = []
playsList = []
with open('forDisplay.txt') as f:
    username = f.readline().strip()
    num_tracks = int(f.readline())
    for line in f:
        loc_of_comma = line.rfind(', ')
        songList.append(line[:loc_of_comma])
        playsList.append(int(line[loc_of_comma + 2:]))

songList.reverse()
playsList.reverse()

fig = go.Figure()

color_labels = [k for k in range(1, num_tracks + 1)]

obj = go.Bar(x=playsList, y=songList, 
             text=playsList, 
             orientation="h",
             marker=dict(color = color_labels, colorscale=[[0, 'rgb(55, 64, 57)'],
                                                        [0.1, 'rgb(55, 64, 57)'],

                                                        [0.9, 'rgb(30, 215, 96)'],
                                                        [1.0, 'rgb(30, 215, 96)']
             ]))

fig.add_trace(obj)

lightgrey = 'rgb(229,236,246)'
fig.update_layout(
    title={
        'text': str(username) + "'s Top " + str(num_tracks) + " Listened Tracks",
        'x': 0.5,
        'xanchor': 'center',
        'yanchor': 'top'
    },
    xaxis=dict(gridcolor='lightgrey'),
    margin=dict(l=100, r = 150, b = 8, pad = 10),
    paper_bgcolor=lightgrey,
    font=dict(
        family="Trebuchet MS, sans-serif",
        size=12,
    )
)
fig.show()