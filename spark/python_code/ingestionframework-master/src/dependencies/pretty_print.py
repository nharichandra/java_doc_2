# -*- coding: utf-8 -*-
"""
Created on 04/12/2020 2:01 PM

@author : Nirav Langaliya
"""

def pretty_print(
        value,
        delimiter='*',
        open_close: bool = True,
        num: int = 120
):
    value = str(value)

    if open_close:
        print("\n\n " + delimiter * num)
        print("\t\t" + value)
        print(delimiter * num + "\n")

    return
