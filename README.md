Prerequisites
-------------
Run in `TomEE 7.0.0-SNAPSHOT webprofile`.

Test
----
Build with maven and run.

Solution
--------
As we are too early in the containers lifecycle, `@Resource` is not yet available.

Using `@Inject`, however, works.