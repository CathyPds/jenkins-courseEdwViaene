job('NodeJS Docker example') {
    scm {
        git('https://github.com/CathyPds/NodeJSEdViaene.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('CathyPds')
            node / gitConfigEmail('catherine_pires@yahoo.fr')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('NodeJS') // this is the name of the NodeJS installation in // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('CathyPds/NodeJSEdViaene')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
