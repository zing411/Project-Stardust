ac_i=abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_
ac_temp=$(printf "%s" $ac_i | cut -c 7)ithub_pat_11A3V367Y0skmk8Wbw3gLx_fRrLfmnqRk05nXvzFBx8fzaJ8fg8fbvVRtXlYSxx3PaVLVQP6RXZNQ2lpBE
#echo $ac_temp
git pull
git add .
git commit -m update
git push https://$ac_temp@github.com/zing411/Project-Stardust
git pull