# Creador de gráficas

# 1. Recibe los dataframes de las VIEWS
# 2. Crea las gráficas con Matplotlib
# 3. Almacena las gráficas en una ruta específica (…/Fronted/assets/images/*)

import io
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def bars_plot(df, xlabel, ylabel, title, columns):
    colors = plt.cm.Paired(np.linspace(0, 1, len(df)))  # Crear colores para las barras
    plt.figure(figsize=(12, 12))  # Ajustar el tamaño de la gráfica
    bars = plt.bar(df[columns[0]], df[columns[1]], color=colors)  # Crear las barras
    plt.xlabel(xlabel)  # Establecer la etiqueta del eje x
    plt.ylabel(ylabel)  # Establecer la etiqueta del eje y
    plt.title(title)  # Establecer el título
    plt.xticks(rotation=45)  # Rotar las etiquetas del eje x

    max_y_value = max(df[columns[1]])  # Obtener el valor máximo en el eje y
    if max_y_value <= 10:
        y_interval = 2
    else:
        y_interval = 10
    
    plt.yticks(np.arange(0, max_y_value + y_interval, y_interval))  # Establecer el rango de valores del eje y de acuerdo al intervalo

    for i, bar in enumerate(bars):  # Añadir líneas horizontales y el valor correspondiente en el eje y
        plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 0.1, 
                    str(int(bar.get_height())), ha='center', va='bottom', fontsize=15)

    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer

def pie_plot(df, title, column):
    plt.figure(figsize=(12, 12)) #Set the size of the plot
    colors = plt.cm.Paired(np.linspace(0, 1, len(df))) #Create colors for the pie chart
    plt.pie(df[column[0]], labels=df[column[0]], autopct='%1.1f%%', startangle=140, colors=colors, textprops={'fontsize': 14}) #Create the pie chart
    plt.axis('equal')  # Equal aspect ratio ensures that pie is drawn as a circle.
    plt.title(title, fontsize=20) #Set the title
    plt.legend(df[column[1]], loc='best', title='Categories', fontsize=15) #Add a legend with the categories
    
    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer

def timeline_plot(df, xlabel, ylabel, title, columns):
    # Crear una columna de fecha combinando año y mes
    df['date'] = pd.to_datetime(df[[columns[2], columns[0]]].assign(day=1))
    
    # Ordenar el DataFrame por fecha
    df = df.sort_values(by='date')
    
    # Crear la gráfica de línea de tiempo
    plt.figure(figsize=(12, 6))  # Ajustar el tamaño de la gráfica
    plt.plot(df['date'], df[columns[1]], marker='o', linestyle='-', color='b')  # Graficar los datos
    plt.xlabel(xlabel)  # Establecer la etiqueta del eje x
    plt.ylabel(ylabel)  # Establecer la etiqueta del eje y
    plt.title(title)  # Establecer el título
    plt.grid(True)  # Añadir una cuadrícula
    plt.xticks(rotation=45)  # Rotar las etiquetas del eje x para una mejor legibilidad
    
    # Añadir etiquetas de valor en cada punto de la gráfica y líneas verticales con anotaciones
    for i, row in df.iterrows():
        plt.text(row['date'], row[columns[1]], f'{row[columns[1]]:.2f}', ha='center', va='bottom', fontsize=10)
        plt.axvline(x=row['date'], color='gray', linestyle='--', linewidth=0.5)
        plt.annotate(f'{row["date"].strftime("%Y-%m")}', 
                    xy=(row['date'], row[columns[1]]),
                    xytext=(row['date'], row[columns[1]] + 50),  # Desplazar un poco el texto hacia arriba
                    ha='center', 
                    arrowprops=dict(facecolor='black', arrowstyle='->'),
                    fontsize=10)

    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer