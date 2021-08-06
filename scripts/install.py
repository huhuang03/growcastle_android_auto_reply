import os
# please run this at root dictionary.
# how to do the install

script_name = 'growcastle_script.sh'
script_path = f"{script_name}"

os.system(f'adb push {script_path} /sdcard/')
os.system(f'adb shell "mv /sdcard/{script_name} /data/local/tmp/"')
os.system(f'adb shell "chmod +x /data/local/tmp/{script_name}"')