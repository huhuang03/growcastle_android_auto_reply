# push this script to /data/local/tmp/ to start this.
FOLDER_NAME="growcastle_script"
FOLDER_PATH="/sdcard/$FOLDER_NAME"
RUN_LOCK_FILE_PATH="$FOLDER_PATH/run.lock"
LOG_FILE_PATH="$FOLDER_PATH/log.log"
mkdir -p $FOLDER_PATH
touch $RUN_LOCK_FILE_PATH

log()
{

}

# for now it's good
while [ -e $RUN_LOCK_FILE_PATH ]
do
    echo "loop!"
    sleep 5s
done