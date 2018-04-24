# Date and Time Picker

Date Picker allows to select consisting of day, month and year in user interface. In this tutorial, we will learn how to implement Date Picker in Android Application. To display DatePickerDialog, we will define DatePickerFragment class that extends DialogFragment and return a DatePickerDialog from the fragment onCreateDialog() method.

# Create DatePickerFragment class

To display Date picker dialog, we will create a DatePickerFragment class that extends DialogFragment. Define the onCreateDialog() method to return an instance of DatePickerDialog.

```
/**
 * Create a DatePickerFragment class that extends DialogFragment. 
 * Define the onCreateDialog() method to return an instance of DatePickerDialog
 */
public static class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), 
                    (DatePickerDialog.OnDateSetListener) 
                            getActivity(), year, month, day);
     }

 }
```



## Authors

* **Muhammad Atif** - *Initial work* - [Muhammad Atif](https://github.com/atifabbasi19)

## License

```
Copyright 2018 Muhammad Atif

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

