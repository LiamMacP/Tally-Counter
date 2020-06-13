package opensource.liamm.tallycounter.data.repository.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import opensource.liamm.tallycounter.utils.StringUtils;

@Entity(tableName = "counters")
public class IntegerCounter {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(defaultValue = StringUtils.EMPTY)
    public String name;

    @ColumnInfo(defaultValue = "0")
    public Integer value;

}
