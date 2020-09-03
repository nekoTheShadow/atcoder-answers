NR==1{c=0;d=$2*$2} NR>1&&$1*$1+$2*$2<=d{c++} END{print c}
