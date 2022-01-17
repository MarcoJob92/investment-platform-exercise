#### Overview

Since this is an application made up of multiple modules that perform different roles, I have kept LocalRunner unchanged, which works as if it was an external application (or a microservice) calling the other modules via API.

I have then updated the `PaymentProcessor` and `Payment` classes to process **incoming bank payments** from CSV file.

I have deleted the `Card` class and moved its attributes to `Payment` because I felt the only relevant attribute was `cardId`, where `status` should belong to the `Payment` as it is a payment status.

`bankAccountId` and `paymentType` are other attributes I added to `Payment`, if the payment type is bank only `bankAccountId` will be populated and vice versa for a card payment.
`fee` is now a *BigDecimal*, which is the return type of `getPaymentFeeRate()`.
`Payment` has now also two different amount attributes: `amount` is the integer number taken from the CSV file and `amountAfterFee` is the remaining amount of money after the fee.

I have added a library to the project which is called *Lombok*.
It is a library that automatically plugs into the IDE standard class methods, such as getters and setters, by simply using one annotation, ``@Data``, and reduces boilerplate code.
Although it has several annotations, I found ``@Data`` to be the most usefull for the majiority of the cases.
Official Website: https://projectlombok.org

The `PropertyHandler` singleton and `app.properties` file are used store and get CSV column numbers, so in case they change for any reason, just the `app.properties` needs to be changed.

Finally, after spotting a few issues in `ShareEngine` class, it was refactored.

Unit Tests were updated, but in particular I added a few new Integration Tests to make sure that everything works as expected.
No validity checks were used tough, so the CSV is always supposed to have correct well-formed data.

To build this project locally with Maven and run the tests:
```
mvn package
```