/*
 *
 * This interface contains the options related methods of TomPlugin.
 *
 */

package jtom;

import jtom.adt.tomsignature.types.*;
import jtom.adt.options.types.*;

public interface TomPluginOptions
{
    public abstract TomOptionList declareOptions();
    public abstract TomOptionList requiredOptions();
    public abstract void setOption(String name, String value);
}
