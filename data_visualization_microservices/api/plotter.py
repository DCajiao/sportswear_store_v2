# Creador de gráficas

# 1. Recibe los dataframes de las VIEWS
# 2. Crea las gráficas con Matplotlib
# 3. Almacena las gráficas en una ruta específica (…/Fronted/assets/images/*)

import io
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def bars_plot(df, xlabel, ylabel, title, columns):
    colors = plt.cm.Paired(np.linspace(0, 1, len(df))) #Create colors for the bars
    plt.figure(figsize=(12, 12)) #Set the size of the plot
    bars = plt.bar(df[columns[0]], df[columns[1]], color=colors) #Create the bars
    plt.xlabel(xlabel) #Set the x label
    plt.ylabel(ylabel) #Set the y label
    plt.title(title) #Set the title
    plt.xticks(rotation=45) #Rotate the x labels

    plt.yticks(np.arange(0, max(df[columns[1]]) + 2, 1)) # Set the y range of values discretely with an additional margin of 2 units

    for i, bar in enumerate(bars): # Add horizontal lines and the corresponding y value
        plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 0.1, 
                str(int(bar.get_height())), ha='center', va='bottom', fontsize=10)

    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer