package ua.advanced.Practical6.observer.git.Implementations;

import ua.advanced.Practical6.observer.git.Interfaces.Repository;
import ua.advanced.Practical6.observer.git.Interfaces.WebHook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryImpl implements Repository {
    List<WebHook> webHooks;
    List<Commit> commits;

    public RepositoryImpl() {
        this.webHooks = new ArrayList<>();
        this.commits = new ArrayList<>();
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        if (!webHooks.isEmpty()) {
            commits.add(commit);
            WebHook commitBranch = findMergeBranch(branch, Event.Type.COMMIT);

            commitBranch.onEvent(new Event(Event.Type.COMMIT, branch, List.of(commit)));
        }
        return commit;
    }

    @Override
    public void merge(String sourceBranchName, String targetBranchName) {
        if (!webHooks.isEmpty()) {
            WebHook sourceBranch = findMergeBranch(sourceBranchName, Event.Type.COMMIT);
            WebHook targetBranch = findMergeBranch(targetBranchName, Event.Type.MERGE);

            if (!Objects.isNull(sourceBranch) && !Objects.isNull(targetBranch)) {

                List<Commit> commits = new ArrayList<>();
                for (var event : sourceBranch.caughtEvents())
                    commits.addAll(event.commits());
                if (!commits.isEmpty())
                    targetBranch.onEvent(new Event(Event.Type.MERGE, targetBranchName, commits));
                webHooks.set(webHooks.indexOf(sourceBranch), new WebHookImpl(sourceBranch.branch(), sourceBranch.type()));
            }
        }
    }

    public WebHook findMergeBranch(String targetBranchName, Event.Type type) {
        return webHooks.stream().filter(event -> event.branch() == targetBranchName && event.type() == type).findAny().orElse(null);
    }
}
