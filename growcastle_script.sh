# 0 or default is repeat
# 1 next

# push this script to /data/local/tmp/ to start this.
FOLDER_NAME="growcastle_script"
FOLDER_PATH="/sdcard/$FOLDER_NAME"
RUN_LOCK_FILE_PATH="$FOLDER_PATH/run.lock"
PAUSE_LOCK_FILE_PATH="$FOLDER_PATH/pause.lock"
LOG_FILE_PATH="$FOLDER_PATH/log.log"


mkdir -p $FOLDER_PATH
touch $RUN_LOCK_FILE_PATH

log()
{

}

repeat()
{
    echo "repeat!"
    input tap 1716 946
}

next()
{
    echo "next"
    input tap 1916 946
}

# for now it's good
while [ -e $RUN_LOCK_FILE_PATH ]
do
    echo "loop"
    if [ -e $PAUSE_LOCK_FILE_PATH ]; then
        echo "pause"
    elif [ $1 = "1" ]; then
        next
    else
        repeat
    fi
    sleep 5s
done