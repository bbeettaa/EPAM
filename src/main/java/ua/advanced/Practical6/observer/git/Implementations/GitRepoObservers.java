package ua.advanced.Practical6.observer.git.Implementations;

import ua.advanced.Practical6.observer.git.Interfaces.Repository;
import ua.advanced.Practical6.observer.git.Interfaces.RepositoryImpl;
import ua.advanced.Practical6.observer.git.Interfaces.WebHook;
import ua.advanced.Practical6.observer.git.Interfaces.WebHookImpl;

import java.util.ArrayList;
import java.util.List;

public class GitRepoObservers {
    public static Repository newRepository() {
        return new RepositoryImpl();
    }

    public static WebHook mergeToBranchWebHook(String branchName) {
        return new WebHookImpl(branchName, Event.Type.MERGE);
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new WebHookImpl(branchName, Event.Type.COMMIT);
    }


}
