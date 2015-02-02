package micc.beaconav.db.dbJSONManager.tableScheme.columnSchema;

/**
 * Created by Nagash on 20/01/2015.
 */

public abstract class ColumnField<T>
{

    private ColumnSchema<T> schema = null;
    private T value;



    public ColumnField() {
        this.schema = null;
        initValue();
    }

    ColumnField<T> initSchema(ColumnSchema<T> finalSchema) {
        if(this.schema == null) {
            this.schema = finalSchema;
            return this;
        }
        else return null;
        // cannot be called 2 times, it's auto called from ColumnSchema.java
        // if you try to call it it will cause to ColumnSchema to generate a null ColumnField
        // when you call newField();
    }

    private void initValue(){
        this.value = generateNewInitValue();
    }

    protected abstract T generateNewInitValue();
    protected abstract T parseString(String newStringToParse);


    public void set(T newVal) {
        this.value = newVal;
    }
    public void set(String newValue) {
        this.value = parseString(newValue);
    }


    public final String columnName(){
        return this.schema.name();
    }

    public final T getValue() {
        return value;
    }


}
