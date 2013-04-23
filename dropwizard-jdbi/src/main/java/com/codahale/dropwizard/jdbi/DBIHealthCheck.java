package com.codahale.dropwizard.jdbi;

import com.yammer.metrics.core.HealthCheck;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class DBIHealthCheck extends HealthCheck {
    private final DBI dbi;
    private final String validationQuery;

    public DBIHealthCheck(DBI dbi, String name, String validationQuery) {
        super(name + "-db");
        this.dbi = dbi;
        this.validationQuery = validationQuery;
    }

    @Override
    protected Result check() throws Exception {
        try (Handle handle = dbi.open()) {
            handle.execute(validationQuery);
        }
        return Result.healthy();
    }
}