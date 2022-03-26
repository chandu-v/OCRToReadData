# Read Data from PDF / Image Using Tessaract

## Aim

To read the details from a scanned copy of manually filled form with a standard template.

## User Journey

1. Upload a scanned copy of the form
2. Review the pre-filled digital details from the system.
    a. Correct the details if needed.
3. Submit the data to save.

## Technical Details

1. Endpoint to upload the form.
2. Create a ML Model to process the prefilled form.
    a. Create a data model that accepts all the major form components like
        i. Text Box
        ii. Radio Button/Check Box
    b. Train the data model.
    c. Test the data model.
3. Display the processed information to the user to review.
    a. Allow user to edit option
4. Save the data when user clicks on submit.
