package com.th.game.growcastle_script.bot_client.boradcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.th.game.growcastle_script.bot_client.lg
import com.th.game.growcastle_script.bot_client.manager.scriptMgr

const val REQ_OP = 1

class OpBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        lg.i("onReceive")
        scriptMgr.switch(context!!)
    }
}