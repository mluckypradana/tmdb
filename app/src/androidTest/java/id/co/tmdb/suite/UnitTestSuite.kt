package id.co.tmdb.suite

import id.co.tmdb.ExampleInstrumentedTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

// Runs all unit tests.
@RunWith(Suite::class)
@Suite.SuiteClasses(ExampleInstrumentedTest::class)
class UnitTestSuite