#!/bin/sh

for f in $(find . -name '*.png')
do 
    f_n="${f%.*}"
    convert $f -resize 1024x1024 $f_n.temp
    rm $f
done

for f in $(find . -name '*.temp')
do 
    f_n="${f%.*}"
    mv $f $f_n.png
done

# for d in $(find . -maxdepth 2 -type d)
# do
#   #Do something, the directory is accessible with $d:
#   echo $d
# done
