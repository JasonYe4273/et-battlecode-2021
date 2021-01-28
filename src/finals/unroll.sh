python3 unrollNavTo.py > UnrolledNavigation.java
sed -i UnrolledNavigation.java -e "s/+ 0,/,/g" -e "s/+ 0)/)/g"
