ac_i=abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_
ac_temp=$(printf "%s" $ac_i | cut -c 7)ithub_pat_1111111111111111
#echo $ac_temp
git pull
git add .
git commit -m update
git push https://$ac_temp@github.com/n01648028/Tasky
git pull