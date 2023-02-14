# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""
def get_epoch():
    import time
    return int(time.time())


def get_timestamp():
    import datetime
    return datetime.datetime.now()


def get_current_date():
    current_day = get_timestamp()
    return int(current_day.strftime('%Y%m%d'))


def get_year_month():
    timestamp = get_timestamp()
    return (timestamp.year, timestamp.month)


def get_year_month_day():
    timestamp = get_timestamp()
    return (timestamp.year, timestamp.month, timestamp.day)


def get_year_month_day_hour():
    timestamp = get_timestamp()
    return (timestamp.year, timestamp.month, timestamp.day, timestamp.hour)


def get_date_hour():
    return get_current_date(), get_timestamp().hour
