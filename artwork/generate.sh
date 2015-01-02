#!/bin/bash

# Epoch Launcher, a free software launcher for Android™, inspired by Sword Art Online.
# Copyright (C) 2015  Miras Absar
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

MDPI_DRAWABLE_DENSITY="160x160"
HDPI_DRAWABLE_DENSITY="240x240"
XHDPI_DRAWABLE_DENSITY="320x320"
XXHDPI_DRAWABLE_DENSITY="480x480"
XXXHDPI_DRAWABLE_DENSITY="640x640"

MDPI_DRAWABLE_DIMENSIONS="48x48"
HDPI_DRAWABLE_DIMENSIONS="72x72"
XHDPI_DRAWABLE_DIMENSIONS="96x96"
XXHDPI_DRAWABLE_DIMENSIONS="144x144"
XXXHDPI_DRAWABLE_DIMENSIONS="192x192"

MDPI_DRAWABLE_FOLDER="../src/main/res/drawable-mdpi"
HDPI_DRAWABLE_FOLDER="../src/main/res/drawable-hdpi"
XHDPI_DRAWABLE_FOLDER="../src/main/res/drawable-xhdpi"
XXHDPI_DRAWABLE_FOLDER="../src/main/res/drawable-xxhdpi"
XXXHDPI_DRAWABLE_FOLDER="../src/main/res/drawable-xxxhdpi"

busybox rm -f $MDPI_DRAWABLE_FOLDER/*
busybox rm -f $HDPI_DRAWABLE_FOLDER/*
busybox rm -f $XHDPI_DRAWABLE_FOLDER/*
busybox rm -f $XXHDPI_DRAWABLE_FOLDER/*
busybox rm -f $XXXHDPI_DRAWABLE_FOLDER/*

mogrify -units PixelsPerInch -density $MDPI_DRAWABLE_DENSITY -resize $MDPI_DRAWABLE_DIMENSIONS -background transparent -format png *.svg
busybox mv *.png $MDPI_DRAWABLE_FOLDER

mogrify -units PixelsPerInch -density $HDPI_DRAWABLE_DENSITY -resize $HDPI_DRAWABLE_DIMENSIONS -background transparent -format png *.svg
busybox mv *.png $HDPI_DRAWABLE_FOLDER

mogrify -units PixelsPerInch -density $XHDPI_DRAWABLE_DENSITY -resize $XHDPI_DRAWABLE_DIMENSIONS -background transparent -format png *.svg
busybox mv *.png $XHDPI_DRAWABLE_FOLDER

mogrify -units PixelsPerInch -density $XXHDPI_DRAWABLE_DENSITY -resize $XXHDPI_DRAWABLE_DIMENSIONS -background transparent -format png *.svg
busybox mv *.png $XXHDPI_DRAWABLE_FOLDER

mogrify -units PixelsPerInch -density $XXXHDPI_DRAWABLE_DENSITY -resize $XXXHDPI_DRAWABLE_DIMENSIONS -background transparent -format png *.svg
busybox mv *.png $XXXHDPI_DRAWABLE_FOLDER
