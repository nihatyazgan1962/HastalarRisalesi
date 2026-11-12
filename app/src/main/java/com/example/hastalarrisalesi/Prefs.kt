package com.example.hastalarrisalesi

import android.content.Context

class Prefs(ctx: Context) {
    private val sp = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var fontSize: Float
        get() = sp.getFloat("font", 20f)
        set(v) = sp.edit().putFloat("font", v).apply()

    var dark: Boolean
        get() = sp.getBoolean("dark", false)
        set(v) = sp.edit().putBoolean("dark", v).apply()

    var favorites: Set<String>
        get() = sp.getStringSet("fav", emptySet()) ?: emptySet()
        set(v) = sp.edit().putStringSet("fav", v).apply()

    var zikir: Int
        get() = sp.getInt("zikir", 0)
        set(v) = sp.edit().putInt("zikir", v).apply()

    var notif: Boolean
        get() = sp.getBoolean("notif", false)
        set(v) = sp.edit().putBoolean("notif", v).apply()

    var hour: Int
        get() = sp.getInt("hour", 9)
        set(v) = sp.edit().putInt("hour", v).apply()

    var speechRate: Float
        get() = sp.getFloat("rate", 0.9f)
        set(v) = sp.edit().putFloat("rate", v).apply()
}
