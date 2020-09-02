package com.android.toastnotificationlibrary.util;// MsgUnread.java

import kotlin.Metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 1, 15},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"},
        d2 = {"Lcom/android/toastnotificationlibrary/entity/MsgUnread;", "", "official", "", "(I)V", "getOfficial", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "toastnotificationlibrary"}
)
public final class MsgUnread {
    private int official;

    public final int getOfficial() {
        return this.official;
    }

    public MsgUnread(int official) {
        this.official = official;
    }

    public MsgUnread() {
    }

    public final int component1() {
        return this.official;
    }

    @NotNull
    public final MsgUnread copy(int official) {
        return new MsgUnread(official);
    }

    // $FF: synthetic method
    @NotNull
    public static MsgUnread copy$default(MsgUnread var0, int var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = var0.official;
        }

        return var0.copy(var1);
    }

    @NotNull
    public String toString() {
        return "MsgUnread(official=" + this.official + ")";
    }

    public int hashCode() {
        return this.official;
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof MsgUnread) {
                MsgUnread var2 = (MsgUnread) var1;
                if (this.official == var2.official) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}