/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.model;

/**
 *
 * @author Administrator
 */
public class DropDown {
    private String selectedValue = "";
    private String[] value;
    private String[] label;
    private String[] dropDownOption;
    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public void setSelectedValue(String selected)
    {
        this.selectedValue = selected;
    }
    
    public String[] getDropDownOption()
    {
        String[] options = new String[value.length];
        for(int i = 0; i < value.length; i++)
        {
            if("".compareTo(selectedValue) > 0)
            {
                options[i] = "<option value='"+value[i]+"'>"+label[i]+"</option>";
            }
            else
            {
                
                if(label[i].equals(selectedValue))
                {
                    options[i] = "<option value='"+value[i]+"' SELECTED>"+label[i]+"</option>";
                }
                else
                {
                    options[i] = "<option value='"+value[i]+"'>"+label[i]+"</option>";
                }
            }
        }
        dropDownOption = options;
        return dropDownOption;
    }
}
