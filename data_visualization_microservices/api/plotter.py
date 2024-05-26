import io
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def bars_plot(df, xlabel, ylabel, title, columns):
    """
    Generate a bar plot from a DataFrame and return the image as a bytes buffer.

    Parameters:
    df (DataFrame): The data frame containing the data to plot.
    xlabel (str): The label for the x-axis.
    ylabel (str): The label for the y-axis.
    title (str): The title of the plot.
    columns (list): A list with two elements indicating the columns to use for the x and y values of the bars.

    Returns:
    BytesIO: A bytes buffer containing the image of the plot in PNG format.
    """
    
    colors = plt.cm.Paired(np.linspace(0, 1, len(df)))
    plt.figure(figsize=(12, 12)) 
    bars = plt.bar(df[columns[0]], df[columns[1]], color=colors)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.title(title)
    plt.xticks(rotation=45)

    max_y_value = max(df[columns[1]])
    if max_y_value <= 10:
        y_interval = 2
    else:
        y_interval = 10
    
    plt.yticks(np.arange(0, max_y_value + y_interval, y_interval))  # Set the range of y-axis values according to the interval

    for i, bar in enumerate(bars):  # Add horizontal lines and the corresponding value on the y-axis
        plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 0.1, 
                    str(int(bar.get_height())), ha='center', va='bottom', fontsize=15)

    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer

def pie_plot(df, title, column):
    """
    Generate a pie chart from a DataFrame and return the image as a bytes buffer.

    Parameters:
    df (DataFrame): The data frame containing the data to plot.
    title (str): The title of the plot.
    column (list): A list with two elements indicating the columns to use for the pie labels and legend categories.

    Returns:
    BytesIO: A bytes buffer containing the image of the plot in PNG format.
    """
    
    plt.figure(figsize=(12, 12)) 
    colors = plt.cm.Paired(np.linspace(0, 1, len(df)))
    plt.pie(df[column[0]], labels=df[column[0]], autopct='%1.1f%%', startangle=140, colors=colors, textprops={'fontsize': 14}) # Create the pie chart
    plt.axis('equal')  # Equal aspect ratio ensures that pie is drawn as a circle.
    plt.title(title, fontsize=20)
    plt.legend(df[column[1]], loc='best', title='Categories', fontsize=15) # Add a legend with the categories
    
    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer

def timeline_plot(df, xlabel, ylabel, title, columns):
    """
    Generate a timeline plot from a DataFrame and return the image as a bytes buffer.

    Parameters:
    df (DataFrame): The data frame containing the data to plot.
    xlabel (str): The label for the x-axis.
    ylabel (str): The label for the y-axis.
    title (str): The title of the plot.
    columns (list): A list with three elements indicating the columns to use for the year, value, and month.

    Returns:
    BytesIO: A bytes buffer containing the image of the plot in PNG format.
    """
    
    df['date'] = pd.to_datetime(df[[columns[2], columns[0]]].assign(day=1)) # Create a date column by combining year and month
    df = df.sort_values(by='date')
    plt.figure(figsize=(12, 8))
    plt.plot(df['date'], df[columns[1]], marker='o', linestyle='-', color='b')  
    plt.xlabel(xlabel) 
    plt.ylabel(ylabel)  
    plt.title(title)  
    plt.grid(True) 
    plt.xticks(rotation=45)  
    
    # Adding value labels at each point on the graph and vertical lines with annotations
    for i, row in df.iterrows():
        plt.text(row['date'], row[columns[1]], f'{row[columns[1]]:.2f}', ha='center', va='bottom', fontsize=10)
        plt.axvline(x=row['date'], color='gray', linestyle='--', linewidth=0.5)
        plt.annotate(f'{row["date"].strftime("%Y-%m")}', 
                    xy=(row['date'], row[columns[1]]),
                    xytext=(row['date'], row[columns[1]] + 50),  # Move the text up a little bit
                    ha='center', 
                    arrowprops=dict(facecolor='black', arrowstyle='->'),
                    fontsize=10)

    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)
    plt.close()
    return img_buffer